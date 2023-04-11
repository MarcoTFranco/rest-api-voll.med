package med.voll.api.domain.user;

public record DataDetailsUser(
        String login
) {
    public DataDetailsUser(User user) {
        this(user.getLogin());
    }
}
