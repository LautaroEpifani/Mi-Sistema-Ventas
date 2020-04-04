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

import com.andyisays.misistemaventas.model.Empresa;
import com.andyisays.misistemaventas.model.Producto;
import com.andyisays.misistemaventas.model.Proveedor;
import com.andyisays.misistemaventas.repository.EmpresasRepository;
import com.andyisays.misistemaventas.repository.ProveedoresRepository;

@Controller
@RequestMapping(path = "/empresas")
public class EmpresasController {
	
	
	 @Autowired
	  private EmpresasRepository empresasRepository;
	
   
	 
	 //----------------MOSTRAR----------------------
	 
	  @GetMapping(value = "/mostrar") //Path desde el nav.
	    public String mostrarProoveedor(Model model) {
	        
		  	model.addAttribute("empresas", empresasRepository.findAll()); // ${proveedores}
	        
	        return "empresas/ver_empresas"; //Carpeta / archivo html.
	    }
	 
	
	//-----------------AGREGAR--------------------------  
	  
	  @GetMapping(value = "/agregar")
	    public String agregarEmpresa(Model model) {
	        model.addAttribute("empresa", new Empresa());
	        return "empresas/agregar_empresa";
	    }
	 
    
	
	  @PostMapping(value = "/agregar")
	    public String guardarProveedor(@ModelAttribute @Valid Empresa empresa, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
	        if (bindingResult.hasErrors()) {
	            return "empresas/agregar_empresa";
	        }
	        if (empresasRepository.findFirstByNombre(empresa.getNombre()) != null) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "Ya existe una empresa con ese nombre")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/empresas/agregar";
	        }
	        empresasRepository.save(empresa);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Agregado correctamente")
	                .addFlashAttribute("clase", "success");
	        return "redirect:/empresas/agregar";
	    }
	  
	  
	  
	  
	  //---------------------EDITAR------------------------------
	  
	  @GetMapping(value = "/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable long id, Model model) {
	        model.addAttribute("empresa", empresasRepository.findById(id).orElse(null));
	        return "empresas/editar_empresa";
	    }
	  
	  
	  @PostMapping(value = "/editar/{id}")
	    public String actualizarProveedor(@ModelAttribute @Valid Empresa empresa, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
	        if (bindingResult.hasErrors()) {
	            if (empresa.getId() != null) {
	                return "empresas/editar_empresa";
	            }
	            return "redirect:/empresas/mostrar";
	        }
	        Empresa posibleEmpresaExistente = empresasRepository.findFirstByNombre(empresa.getNombre());

	        if (posibleEmpresaExistente != null && !posibleEmpresaExistente.getId().equals(empresa.getId())) {
	            redirectAttrs
	                    .addFlashAttribute("mensaje", "Ya existe una empresa con ese nombre")
	                    .addFlashAttribute("clase", "warning");
	            return "redirect:/empresas/agregar";
	        }
	        empresasRepository.save(empresa);
	        redirectAttrs
	                .addFlashAttribute("mensaje", "Editado correctamente")
	                .addFlashAttribute("clase", "success");
	        return "redirect:/empresas/mostrar";
	    }
	
	
	  	
	  	//------------------------ELIMINAR-----------------------


		@PostMapping(value = "/eliminar")
		public String eliminarProveedor(@ModelAttribute Empresa empresa, RedirectAttributes redirectAttrs) {
			redirectAttrs
            .addFlashAttribute("mensaje", "Eliminado correctamente")
            .addFlashAttribute("clase", "warning");
			empresasRepository.deleteById(empresa.getId());
			return "redirect:/empresas/mostrar";
		}
		
		
}

