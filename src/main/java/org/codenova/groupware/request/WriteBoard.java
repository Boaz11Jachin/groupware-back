package org.codenova.groupware.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WriteBoard {

    @NotNull
    private String writer;

    @NotBlank
    private String title;

    private String content;
}
