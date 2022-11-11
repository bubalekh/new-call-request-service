package pw.cyberbrain.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallRequestDto {
    private String name;
    private String phone;
    private String date;
    private String time;
    private Long userId;

    public static CallRequestDto getCallRequestDto(String request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(request, CallRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromCallRequestDto(CallRequestDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

