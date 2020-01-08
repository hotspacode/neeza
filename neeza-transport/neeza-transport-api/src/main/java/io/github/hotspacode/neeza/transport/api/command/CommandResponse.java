package io.github.hotspacode.neeza.transport.api.command;

public class CommandResponse<R> {

    private final boolean success;
    private final R result;
    private final Throwable exception;

    private CommandResponse(R result) {
        this(result, true, null);
    }

    private CommandResponse(R result, boolean success, Throwable exception) {
        this.success = success;
        this.result = result;
        this.exception = exception;
    }

    /**
     * Construct a successful response with given object.
     *
     * @param result result object
     * @param <T>    type of the result
     * @return constructed server response
     */
    public static <T> CommandResponse<T> ofSuccess(T result) {
        return new CommandResponse<T>(result);
    }

    /**
     * Construct a failed response with given exception.
     *
     * @param ex cause of the failure
     * @return constructed server response
     */
    public static <T> CommandResponse<T> ofFailure(Throwable ex) {
        return new CommandResponse<T>(null, false, ex);
    }

    /**
     * Construct a failed response with given exception.
     *
     * @param ex     cause of the failure
     * @param result additional message of the failure
     * @return constructed server response
     */
    public static <T> CommandResponse<T> ofFailure(Throwable ex, T result) {
        return new CommandResponse<T>(result, false, ex);
    }

    public boolean isSuccess() {
        return success;
    }

    public R getResult() {
        return result;
    }

    public Throwable getException() {
        return exception;
    }
}
