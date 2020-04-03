package com.andyisays.misistemaventas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andyisays.misistemaventas.model.Producto;
import com.andyisays.misistemaventas.model.Proveedor;
import com.andyisays.misistemaventas.repository.ProveedoresRepository;

@Controller
@RequestMapping(path = "/proveedores")
public class ProveedoresController {
	
	
	 @Autowired
	  private ProveedoresRepository proveedoresRepository;
	
   
	 
	 //----------------MOSTRAR----------------------
	 
	  @GetMapping(value = "/mostrar") //Path desde el nav.
	    public String mostrarProoveedor(Model model) {
	        
		  	model.addAttribute("proveedores", proveedoresRepository.findAll()); // ${proveedores}
	        
	        return "proveedores/ver_proveedores"; //Carpeta / archivo html.
	    }
	 
	
	//-----------------AGREGAR--------------------------  
	  
	  @GetMapping(value = "/agregar")
	    public String agregarProveedor(Model model) {
	        model.addAttribute("proveedor", new Proveedor());
	        return "proveedores/agregar_proveedor";
	    }
	 
    
	
	  @PostMapping(value = "/agregar")
	    public String guardarProveedor(@ModelAttribute @Valid Proveedor proveedor, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
	        if (bindingResult.hasErrors()) {
	            return "proveedores/agregar_producto";
	        }
	        if (proveedoresRepository.findFirstByNombre(proveedor.getNombre()) != null) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "Ya existe un proveedor con ese nombre")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/proveedores/agregar";
	        }
	        proveedoresRepository.save(proveedor);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Agregado correctamente")
	                .addFlashAttribute("clase", "success");
	        return "redirect:/proveedores/agregar";
	    }
	  
	  
	  
	  
	  //---------------------EDITAR------------------------------
	  
	  @GetMapping(value = "/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable long id, Model model) {
	        model.addAttribute("proveedor", proveedoresRepository.findById(id).orElse(null));
	        return "proveedores/editar_proveedor";
	    }
	  
	  
	  @PostMapping(value = "/editar/{id}")
	    public String actualizarProveedor(@ModelAttribute @Valid Proveedor proveedor, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
	        if (bindingResult.hasErrors()) {
	            if (proveedor.getId() != null) {
	                return "proveedores/editar_proveedor";
	            }
	            return "redirect:/proveedores/mostrar";
	        }
	        Proveedor posibleProveedorExistente = proveedoresRepository.findFirstByNombre(proveedor.getNombre());

	        if (posibleProveedorExistente != null && !posibleProveedorExistente.getId().equals(proveedor.getId())) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "Ya existe un proveedor con ese nombre")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/proveedores/agregar";
	        }
	        proveedoresRepository.save(proveedor);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Editado correctamente")
	                .addFlashAttribute("clase", "success");
	        return "redirect:/proveedores/mostrar";
	    }
	
	
	  	
	  	//------------------------ELIMINAR-----------------------


		@PostMapping(value = "/eliminar")
		public String eliminarProveedor(@ModelAttribute Proveedor proveedor, RedirectAttributes redirectAttrs) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Eliminado correctamente")
            .addFlashAttribute("clase", "warning");
			proveedoresRepository.deleteById(proveedor.getId());
			return "redirect:/proveedores/mostrar";
		}
		
		
}

