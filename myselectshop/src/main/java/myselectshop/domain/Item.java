package myselectshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myselectshop.dto.ItemRequestDto;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "item") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Item  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String price;

    @ManyToOne(fetch = FetchType.LAZY) // 상품 조회마다 고객의 정보가 필요하지 않음
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @Column(nullable = false)
//    private int myprice;
//
//    @ManyToOne(fetch = FetchType.LAZY) // 상품 조회마다 고객의 정보가 필요하지 않음
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @OneToMany(mappedBy = "product")
//    private List<ProductFolder> productFolderList = new ArrayList<>();

    public Item(ItemRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.price = requestDto.getPrice();
        this.user = user;
    }
//
//    public void update(ProductMypriceRequestDto requestDto) {
//        this.myprice = requestDto.getMyprice();
//    }
//
//    public void updateByItemDto(ItemDto itemDto) {
//        this.lprice = itemDto.getLprice();
//    }
}