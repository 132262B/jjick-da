package app.jjickda.api.statistics.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("newUsersCountByDate")
public class NewUsersCountByDate {

    private String date;

    private int count;

    @Builder
    public NewUsersCountByDate(String date, int count) {
        this.date = date;
        this.count = count;
    }
}