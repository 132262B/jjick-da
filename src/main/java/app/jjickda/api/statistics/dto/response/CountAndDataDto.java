package app.jjickda.api.statistics.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("countAndDataDto")
public class CountAndDataDto {

    private String data;

    private int count;

    @Builder
    public CountAndDataDto(String data, int count) {
        this.data = data;
        this.count = count;
    }
}