package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by reve99 on 4/24/2016.
 */
@Controller
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public void setCustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listProducts(Model model){
        model.addAttribute("customers",customerService.listAllCustomers());

        return "customers";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer",customerService.getCustomerById(id));
        return "customer";
    }

    @RequestMapping("/customer/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("customer",customerService.getCustomerById(id));
        return "customerform";
    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "customerform";
    }

    @RequestMapping(value = "/customer",method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer){
        Customer savedCustomer = customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customer/"+ savedCustomer.getId();
    }

    @RequestMapping("/customer/delete/{id}")
    public String delete(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return "redirect:/customer";
    }

}
