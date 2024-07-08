package myselectshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import myselectshop.domain.Item;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;

    private String username;

    private String title;

    private String content;

    private String price;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.username = item.getUser().getUsername();
        this.price = item.getPrice();
    }
}
