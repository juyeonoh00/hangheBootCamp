package myselectshop.service;

import lombok.RequiredArgsConstructor;
import myselectshop.domain.Item;
import myselectshop.domain.User;
import myselectshop.dto.ItemRequestDto;
import myselectshop.dto.ItemResponseDto;
import myselectshop.repository.ItemRepository;
import myselectshop.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    public ItemResponseDto createItem(ItemRequestDto requestDto, User user) {
        Item item = itemRepository.save(new Item(requestDto, user));
        return new ItemResponseDto(item);
    }

    public List<ItemResponseDto> getItemList() {

        List<Item> itemList = itemRepository.findAll();

        return itemList.stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

    public ItemResponseDto updateItem(Long id, ItemRequestDto requestDto, UserDetailsImpl userDetails) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id " + id + " does not exist."));

        //유저 확인
        if(userDetails.getUsername().equals(item.getUser().getUsername())){
            item.setTitle(requestDto.getTitle());
            item.setContent(requestDto.getContent());
            item.setPrice(requestDto.getPrice());

            Item updateItem = itemRepository.save(item);
            return new ItemResponseDto(updateItem);
        }
        else {
            throw new IllegalArgumentException("User does not have permission to update this item.");
        }
    }

    public void deleteItem(Long id, UserDetailsImpl userDetails) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id " + id + " does not exist."));

        //유저 확인
        if(userDetails.getUsername().equals(item.getUser().getUsername())){
            itemRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("User does not have permission to update this item.");
        }
    }

}
