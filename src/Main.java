import controller.AccountOperationController;
import util.CURDUtil;
import util.GeneralJDBCComponent;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AccountOperationController.run();
    }
}