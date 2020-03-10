package database;

import javafx.scene.control.PasswordField;

import java.awt.*;
import java.io.Serializable;

public class UserCon implements Serializable {
public String login;
public String password;
public boolean canConnect;
}
