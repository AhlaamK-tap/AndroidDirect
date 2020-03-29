package company.tap.ibtikarat.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import company.tap.ibtikarat.enums.URLStatus;


/**
 * The type Tracking url.
 */
public class TrackingURL implements Serializable {

    /**
     * The Status.
     */
    @SerializedName("status")
    @Expose
    @NonNull
    URLStatus status = URLStatus.PENDING;

    /**
     * The Url.
     */
    @SerializedName("url")
    @Expose
    @Nullable
    String url;

    /**
     * Instantiates a new Tracking url.
     *
     * @param url the url
     */
    public TrackingURL(@NonNull String url) {

        this.url = url;
        this.status = URLStatus.PENDING;
    }
}
