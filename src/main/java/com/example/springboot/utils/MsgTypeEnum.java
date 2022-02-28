import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * The MsgTypeEnum enumeration.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum MsgTypeEnum {

    OPERATION("运营通告"),
    MARKETING("营销通知"),
    SERVICE("服务通知"),
    OTHER("其他");

    private String name;
    private String desc;

    MsgTypeEnum(String desc) {
        this.desc = desc;
        this.name = this.name();
    }
    public static MsgTypeEnum[] getMsgTypeEnum() {
        return MsgTypeEnum.values();
    }

}
