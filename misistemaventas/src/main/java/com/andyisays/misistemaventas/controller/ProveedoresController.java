package com.andyisays.misistemaventas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andyisays.misistemaventas.model.Proveedor;
import com.andyisays.misistemaventas.repository.ProveedoresRepository;

@Controller
@RequestMapping(path = "/proveedores")
public class ProveedoresController {
	
	
	 @Autowired
	  private ProveedoresRepository proveedoresRepository;
	
   
	 
	 
	 
	  @GetMapping(value = "/mostrar") //Path desde el nav.
	    public String mostrarProductos(Model model) {
	        
		  	model.addAttribute("proveedores", proveedoresRepository.findAll()); // ${proveedores}
	        
	        return "proveedores/ver_proveedores"; //Cartpeta / archivo html.
	    }
	 
	 
    
	
	
	/*@PostMapping(value="/addproveedor")
	public String addProveedor(@Valid Proveedor proveedor, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-proveedor";
		}
		
	
		
		proveedor = proveedoresRepository.save(proveedor);
		
		
		
		return "redirect:/productos/agregar";
	}*/

}
