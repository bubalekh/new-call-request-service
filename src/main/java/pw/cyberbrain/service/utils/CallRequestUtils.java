package pw.cyberbrain.service.utils;

import pw.cyberbrain.service.dto.CallRequestDto;

public class CallRequestUtils {
    public static String getNewRequestNotificationText(CallRequestDto request) {
        return "Пользователь "
                + request.getName()
                + " запланировал звонок с Вами на "
                + request.getDate()
                + " в "
                + request.getTime()
                + ". Номер телефона: "
                + getParsedPhone(request.getPhone());
    }

    public static String getParsedPhone(String phone) {
        var temp = phone.replaceAll("\\D", "");
        return "+7" + temp.substring(temp.length() - 10);
    }
}
