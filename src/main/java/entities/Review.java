package entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Review extends BaseEntity{
    Long rating;
    String message;
    String createdBy;

    public Review(Long rating, String createdBy) {
        this.rating = rating;
        this.createdBy = createdBy;
    }
}
