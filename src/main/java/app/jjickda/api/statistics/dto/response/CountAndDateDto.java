package app.jjickda.api.statistics.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("countAndDateDto")
public class CountAndDateDto {

    private String date;

    private int count;

    @Builder
    public CountAndDateDto(String date, int count) {
        this.date = date;
        this.count = count;
    }
}