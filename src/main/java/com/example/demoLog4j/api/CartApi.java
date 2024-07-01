package com.example.demoLog4j.api;

import com.example.demoLog4j.dto.CartDto;
import com.example.demoLog4j.dto.UserDto;
import com.example.demoLog4j.dto.request.CartRequest;
import com.example.demoLog4j.service.CartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartApi {

    @Autowired
    private CartService cartService;
    private final Logger logger = LogManager.getLogger(CartApi.class);

    @GetMapping()
    public ResponseEntity<?> getCart(HttpSession httpSession){
        UserDto loggedUser = (UserDto) httpSession.getAttribute("user");
        if (loggedUser == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "You must login");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        CartDto cartDto = cartService.getCartByUserId(loggedUser.getId());
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(HttpSession httpSession, @Valid @RequestBody CartRequest cartRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errors.add(error.getField() + ":" + error.getDefaultMessage());
                logger.error("Bad request: " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        UserDto loggedUser = (UserDto) httpSession.getAttribute("user");
        if (loggedUser == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "You must login");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        CartDto cartDto = cartService.addToCart(loggedUser.getId(), cartRequest.getProductId(), cartRequest.getQuantity());
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @PutMapping("/cart-item/{cartItemId}")
    public ResponseEntity<?> updateCartItemQuantity(HttpSession httpSession, @PathVariable("cartItemId") Long id, @RequestParam Integer quantity){
        UserDto loggedUser = (UserDto) httpSession.getAttribute("user");
        if (loggedUser == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "You must login");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        CartDto cartDto = cartService.updateCartItemQuantity(loggedUser.getId(), id, quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(HttpSession httpSession, @PathVariable("cartItemId") Long id){
        UserDto loggedUser = (UserDto) httpSession.getAttribute("user");
        if (loggedUser == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "You must login");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        CartDto cartDto = cartService.deleteCartItem(loggedUser.getId(), id);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}
