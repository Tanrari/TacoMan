package sia.tacocloud.tacos.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.tacos.dto.TacoOrder;
import sia.tacocloud.tacos.dto.User;
import sia.tacocloud.tacos.repos.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@AllArgsConstructor
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {
    private  int pageSize = 20;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private final OrderRepository orderRepo;

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model){

        Pageable pageable = PageRequest.of(0,pageSize);
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "orderForm";
        }
          log.info("Order submitted: {}", order);
        orderRepo.save(order);
        order.setUser(user);
        System.out.println(order.getUser());
        sessionStatus.setComplete();
        System.out.println("Order saved");
        return "redirect:/";
    }

}
