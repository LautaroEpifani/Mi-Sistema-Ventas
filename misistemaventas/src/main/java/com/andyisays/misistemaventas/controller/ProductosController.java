package com.andyisays.misistemaventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andyisays.misistemaventas.model.Producto;
import com.andyisays.misistemaventas.model.Proveedor;
import com.andyisays.misistemaventas.repository.ProductosRepository;
import com.andyisays.misistemaventas.repository.ProveedoresRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/productos")
public class ProductosController {
    @Autowired
    private ProductosRepository productosRepository;
    //MIERDA
    
    @Autowired
    private ProveedoresRepository proveedoresRepository;

   

    @GetMapping(value = "/mostrar")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productosRepository.findAllProduProveedor()); 
        return "productos/ver_productos";
    }

 
 //----------------------------Agregar-------------------------- 
    
    @GetMapping(value = "/agregar")
    public String agregarProducto(Model model) {
    	
    	model.addAttribute("listaProveedores", proveedoresRepository.findAll());
		
        model.addAttribute("producto", new Producto());
      
        return "productos/agregar_producto";
    }
    
    
    

    @PostMapping(value = "/agregar")
    public String guardarProducto( @ModelAttribute @Valid Producto producto, 
    		BindingResult bindingResult, RedirectAttributes redirectAttrs) {
    	
    	
    	
        if (bindingResult.hasErrors()) {
            return "productos/agregar_producto";
        }
        if (productosRepository.findFirstByCodigo(producto.getCodigo()) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        
        
       
        
        
		
        productosRepository.save(producto);
        
       
       
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }
    
    
   
           
    	
    
    
    
	
		

    
    
    
    
//-------------------------EDITAR------------------------------ 
    
    
    @GetMapping(value = "/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productosRepository.findById(id).orElse(null));
        return "productos/editar_producto";
    }

	
    
    @PostMapping(value = "/editar/{id}")
    public String actualizarProducto(@ModelAttribute @Valid Producto producto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (producto.getId() != null) {
                return "productos/editar_producto";
            }
            return "redirect:/productos/mostrar";
        }
        Producto posibleProductoExistente = productosRepository.findFirstByCodigo(producto.getCodigo());

        if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        productosRepository.save(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/mostrar";
    }
   
    
//----------------------------ELIMINAR-----------------
    
    @PostMapping(value = "/eliminar")
    public String eliminarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        productosRepository.deleteById(producto.getId());
        return "redirect:/productos/mostrar";
    }



    
}
