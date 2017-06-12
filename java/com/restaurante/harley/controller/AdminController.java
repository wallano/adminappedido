package com.restaurante.harley.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurante.harley.dao.CategorieDAO;
import com.restaurante.harley.dao.ClientDAO;
import com.restaurante.harley.dao.InventoryDAO;
import com.restaurante.harley.dao.OrderDAO;
import com.restaurante.harley.dao.ProductDAO;
import com.restaurante.harley.dao.SupplierDAO;
import com.restaurante.harley.model.CategorieInfo;
import com.restaurante.harley.model.ClientInfo;
import com.restaurante.harley.model.InventoryInfo;
import com.restaurante.harley.model.OrderDetailInfo;
import com.restaurante.harley.model.OrderInfo;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.ProductInfo;
import com.restaurante.harley.model.SupplierInfo;
import com.restaurante.harley.validator.ProductInfoValidator;

@Controller
public class AdminController {
	
	final static String IMAGE_RESOURCE_PATH_CATEGORIES = "resource/images/categories";
	
	final static String IMAGE_RESOURCE_PATH_PRODUCTS = "resource/images/products";
	
	@Autowired
    private OrderDAO orderDAO;
 
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private CategorieDAO categorieDAO;
 
    @Autowired
    private ProductInfoValidator productInfoValidator;
    
    @Autowired
    private SupplierDAO supplierDAO;
    
    @Autowired
    private ClientDAO clientDAO;
    
    @Autowired
    private InventoryDAO inventoryDAO;
 

    @Autowired
    private ResourceBundleMessageSource messageSource;
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == ProductInfo.class) {
            dataBinder.setValidator(productInfoValidator);
 
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
 

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
 
    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
 
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
 
    @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
        PaginationResult<OrderInfo> paginationResult //
        = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
 
        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }
 
    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;
        List<CategorieInfo> listCategories = categorieDAO.listCategories();
        if (code != null && code.length() > 0) {
            productInfo = productDAO.findProductInfo(code);
        }
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setNewProduct(true);
        }
        model.addAttribute("productForm", productInfo);
        model.addAttribute("listCategories", listCategories);
        return "product";
    }
 
    @RequestMapping(value = { "/product" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model, //
            @ModelAttribute("productForm") @Validated ProductInfo productInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "product";
        }
        try {
            productDAO.save(productInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "product";
 
        }
        return "redirect:/productList";
    }
    
    @RequestMapping(value = { "/categorie" }, method = RequestMethod.GET)
    public String categorie(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        CategorieInfo categorieInfo = null;
 
        if (code != null && code.length() > 0) {
        	categorieInfo = categorieDAO.findCategorieInfo(code);
        }
        if (categorieInfo == null) {
        	categorieInfo = new CategorieInfo();
        	categorieInfo.setNewCategorie(true);
        }
        model.addAttribute("categorieForm", categorieInfo);
        return "categorie";
    }
    
    @RequestMapping(value = { "/categorie" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String categorieSave(Model model, //
            @ModelAttribute("categorieForm") @Validated CategorieInfo categorieInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "categorie";
        }
        try {
//        	byte[] bytes = file.getBytes();
//            Path path = Paths.get(IMAGE_RESOURCE_PATH_CATEGORIES + file.getOriginalFilename());
//            Files.write(path, bytes);
        	categorieDAO.save(categorieInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            e.printStackTrace();
            return "categorie";
 
        }
        return "redirect:/categorieList";
    }
    
    @RequestMapping(value = { "/categorieDelete" }, method = RequestMethod.GET)
    public String categorieDelete(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        CategorieInfo categorieInfo = null;
 
        if (code != null && code.length() > 0) {
        	categorieInfo = categorieDAO.findCategorieInfo(code);
        }

        if (categorieInfo == null) {
        	categorieInfo = new CategorieInfo();
        	categorieInfo.setNewCategorie(true);
        }else{
        	categorieDAO.deleteCategorieInfo(code);
        }
        
        return "redirect:/categorieList";
    }
    
    @RequestMapping(value = { "/supplier" }, method = RequestMethod.GET)
    public String supplier(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        SupplierInfo supplierInfo = null;
 
        if (code != null && code.length() > 0) {
        	supplierInfo = supplierDAO.findSupplierInfo(code);
        }
        if (supplierInfo == null) {
        	supplierInfo = new SupplierInfo();
        	supplierInfo.setNewSupplier(true);
        }
        model.addAttribute("supplierForm", supplierInfo);
        return "supplier";
    }
    
    @RequestMapping(value = { "/supplier" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String supplierSave(Model model, //
            @ModelAttribute("supplierForm") @Validated SupplierInfo supplierInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "supplier";
        }
        try {
        	supplierDAO.save(supplierInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            e.printStackTrace();
            return "supplier";
 
        }
        return "redirect:/supplierList";
    }
    
    @RequestMapping(value = { "/supplierDelete" }, method = RequestMethod.GET)
    public String supplierDelete(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        SupplierInfo supplierInfo = null;
 
        if (code != null && code.length() > 0) {
        	supplierInfo = supplierDAO.findSupplierInfo(code);
        }

        if (supplierInfo == null) {
        	supplierInfo = new SupplierInfo();
        	supplierInfo.setNewSupplier(true);
        }else{
        	supplierDAO.deleteSupplierInfo(code);
        }
        
        return "redirect:/supplierList";
    }
    
    @RequestMapping(value = { "/client" }, method = RequestMethod.GET)
    public String client(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ClientInfo clientInfo = null;
 
        if (code != null && code.length() > 0) {
        	clientInfo = clientDAO.findClientInfo(code);
        }
        if (clientInfo == null) {
        	clientInfo = new ClientInfo();
        	clientInfo.setNewClient(true);
        }
        model.addAttribute("clientForm", clientInfo);
        return "client";
    }
    
    @RequestMapping(value = { "/client" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String supplierSave(Model model, //
            @ModelAttribute("clientForm") @Validated ClientInfo clientInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "client";
        }
        try {
        	clientDAO.save(clientInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            e.printStackTrace();
            return "client";
 
        }
        return "redirect:/clientList";
    }
    
    @RequestMapping(value = { "/clientDelete" }, method = RequestMethod.GET)
    public String clientDelete(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ClientInfo clientInfo = null;
 
        if (code != null && code.length() > 0) {
        	clientInfo = clientDAO.findClientInfo(code);
        }

        if (clientInfo == null) {
        	clientInfo = new ClientInfo();
        	clientInfo.setNewClient(true);
        }else{
        	clientDAO.deleteClientInfo(code);
        }
        
        return "redirect:/clientList";
    }
    
    @RequestMapping(value = { "/inventory" }, method = RequestMethod.GET)
    public String inventory(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        InventoryInfo inventoryInfo = null;
 
        if (code != null && code.length() > 0) {
        	inventoryInfo = inventoryDAO.findInventoryInfo(code);
        }
        if (inventoryInfo == null) {
        	inventoryInfo = new InventoryInfo();
        	inventoryInfo.setNewInventory(true);
        }
        model.addAttribute("inventoryForm", inventoryInfo);
        return "inventory";
    }
    
    @RequestMapping(value = { "/inventory" }, method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NEVER)
    public String inventorySave(Model model, //
            @ModelAttribute("inventoryForm") @Validated InventoryInfo inventoryInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "inventory";
        }
        try {
        	inventoryDAO.save(inventoryInfo);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            e.printStackTrace();
            return "inventory";
 
        }
        return "redirect:/inventoryList";
    }
    
    @RequestMapping(value = { "/inventoryDelete" }, method = RequestMethod.GET)
    public String inventoryDelete(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        InventoryInfo inventoryInfo = null;
 
        if (code != null && code.length() > 0) {
        	inventoryInfo = inventoryDAO.findInventoryInfo(code);
        }

        if (inventoryInfo == null) {
        	inventoryInfo = new InventoryInfo();
        	inventoryInfo.setNewInventory(true);
        }else{
        	inventoryDAO.deleteInventoryInfo(code);
        }
        
        return "redirect:/inventoryList";
    }
    
    @RequestMapping(value = { "/productDelete" }, method = RequestMethod.GET)
    public String productDelete(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;
 
        if (code != null && code.length() > 0) {
        	productInfo = productDAO.findProductInfo(code);
        }

        if (productInfo == null) {
        	productInfo = new ProductInfo();
        	productInfo.setNewProduct(true);
        }else{
        	productDAO.deleteProductInfo(code);
        }
        
        return "redirect:/productList";
    }
 
    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);
 
        model.addAttribute("orderInfo", orderInfo);
 
        return "order";
    }

}
