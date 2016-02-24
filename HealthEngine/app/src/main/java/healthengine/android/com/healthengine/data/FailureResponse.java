package healthengine.android.com.healthengine.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FailureResponse implements DataInter {

    @SerializedName("errors")
    @Expose
    private List<Error> errors = new ArrayList<Error>();

    /**
     * @return The errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * @param errors The errors
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public class Error {

        @SerializedName("parameter")
        @Expose
        private String parameter;
        @SerializedName("message")
        @Expose
        private String message;

        /**
         * @return The parameter
         */
        public String getParameter() {
            return parameter;
        }

        /**
         * @param parameter The parameter
         */
        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        /**
         * @return The message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

    }

}

