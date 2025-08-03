package com.example.hotel_catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель контактов отеля")
public class ContactDto {

    @NotNull(message = "Номер телефона не может быть пустым")
    @Size(max = 50, message = "Длина номера телефона не может превышать 50 символов")
    @Schema(description = "Номер телефона", example = "+375 17 309-80-00")
    private String phone;

    @NotNull(message = "Постовый адрес не может быть пустым")
    @Size(max = 250, message = "Длина почтового адреса не может превышать 250 символов")
    @Schema(description = "Почтовый адрес", example = "doubletreeminsk.info@hilton.com")
    private String email;

}
