package app.jjickda.global.config.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpLoadFileInfo {

    String originalName;

    String serverName;

    String extension;

    long size;

}
