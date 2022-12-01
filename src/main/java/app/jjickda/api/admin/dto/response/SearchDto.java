package app.jjickda.api.admin.dto.response;

import lombok.*;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("searchDto")
public class SearchDto {
    private String searchObject;
    private String sort;
}
