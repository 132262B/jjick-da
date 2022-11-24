package app.jjickda.global.config.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("upLoadFileInfo")
public class UpLoadFileInfo {

    long multiMediaIdx;

    String originalName;

    String fileId;
    String extension;

    long size;


}
