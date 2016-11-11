package android.live2;


/**
 * Created by cuser on 2016/11/11.
 */

public class PartnerMsg {
    String action;
    String tokenId;
    String fromMemId;
    String toMemId;
    String message;

    public PartnerMsg(){

    }

    public PartnerMsg(String action, String tokenId, String fromMemId, String toMemId, String message) {
        this.action = action;
        this.tokenId = tokenId;
        this.fromMemId = fromMemId;
        this.toMemId = toMemId;
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getFromMemId() {
        return fromMemId;
    }

    public void setFromMemId(String fromMemId) {
        this.fromMemId = fromMemId;
    }

    public String getToMemId() {
        return toMemId;
    }

    public void setToMemId(String toMemId) {
        this.toMemId = toMemId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
