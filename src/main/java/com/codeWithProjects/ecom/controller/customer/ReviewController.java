package com.codeWithProjects.ecom.controller.customer;

import com.codeWithProjects.ecom.dto.OrderedProductResponseDto;
import com.codeWithProjects.ecom.dto.ReviewDto;
import com.codeWithProjects.ecom.entity.Order;
import com.codeWithProjects.ecom.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId)  {
        return ResponseEntity.ok(reviewService.getOrderedProductDetailsByOrderId(orderId));

    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);

        if (reviewDto1 == null)return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);

    }
}
