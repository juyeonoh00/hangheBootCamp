package myselectshop.controller;


import lombok.RequiredArgsConstructor;
import myselectshop.dto.ItemRequestDto;
import myselectshop.dto.ItemResponseDto;

import myselectshop.security.UserDetailsImpl;
import myselectshop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/post")
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ItemResponseDto res = itemService.createItem(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/post")
    public ResponseEntity<List<ItemResponseDto>> getItemList(){
        List<ItemResponseDto> res = itemService.getItemList();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    @PutMapping("/products/{id}")
    public ItemResponseDto updateItem(@PathVariable Long id, @RequestBody ItemRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return itemService.updateItem(id, requestDto, userDetails);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, String>> deleteItem(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            itemService.deleteItem(id, userDetails);
            Map<String, String> res = new HashMap<>();
            res.put("msg", "삭제완료");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }catch (Exception e){
            Map<String, String> res = new HashMap<>();
            res.put("msg", "삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

//    @GetMapping("/products")
//    public Page<ItemResponseDto> getProducts(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam("isAsc") boolean isAsc,
//            @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return productService.getProducts(userDetails.getUser(), page-1, size, sortBy, isAsc);
//    }
//
//    @PostMapping("/products/{productId}/folder")
//    public void addFolder(
//        @PathVariable Long productId,
//        @RequestParam Long folderId,
//        @AuthenticationPrincipal UserDetailsImpl userDetails)
//    {
//        productService.addFolder(productId, folderId, userDetails.getUser());
//    }
//
//    @GetMapping("/folders/{folderId}/products")
//    public Page<ItemResponseDto> getProductsInFolder(
//        @PathVariable Long folderId,
//        @RequestParam("page") int page,
//        @RequestParam("size") int size,
//        @RequestParam("sortBy") String sortBy,
//        @RequestParam("isAsc") boolean isAsc,
//        @AuthenticationPrincipal UserDetailsImpl userDetails
//    ){
//        return productService.getProductsInFolder(
//            folderId,
//            page-1,
//            size,
//            sortBy,
//            isAsc,
//            userDetails.getUser()
//        );
//    }
}
