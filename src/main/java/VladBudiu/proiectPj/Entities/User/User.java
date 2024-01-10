package VladBudiu.proiectPj.Entities.User;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="nivelAcess")
    private NivelAcess nivelAccess;



    public User()
    {
        this.nivelAccess = NivelAcess.VIZITATOR;
    }

    public User(final String username, final String password)
    {
        this.username = username;
        this.password = password;
        this.nivelAccess = nivelAccess.VIZITATOR;
    }

    public boolean isOperator()
    {
        return nivelAccess == nivelAccess.OPERATOR;
    }

    public boolean hasPassword(final String password)
    {
        return this.password.equals(password);
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public NivelAcess getnivelAccess()
    {
        return this.nivelAccess;
    }

    public void setnivelAccess(final NivelAcess nivelAccess)
    {
        this.nivelAccess = nivelAccess;
    }


}
