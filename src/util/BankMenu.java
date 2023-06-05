package util;

import entity.CardInfoEntity;
import service.AccountoperationService;
import service.impl.AccountoperationServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
public class BankMenu {
    public static void manu() {
        AccountoperationService accountoperationService = new AccountoperationServiceImpl();
        CardInfoEntity user = new CardInfoEntity();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 7) {
            System.out.println("请选择您要进行的操作：");
            System.out.println("1. 开户");
            System.out.println("2. 销户");
            System.out.println("3. 挂失");
            System.out.println("4. 补办");
            System.out.println("5. 修改密码");
            System.out.println("6. 存款");
            System.out.println("7. 取款");
            System.out.println("8. 退出");
            while(choice == 0) {
                try{
                    choice = Integer.parseInt(sc.nextLine());
                }catch (Exception e) {
                }
            }
            String password = null;
            boolean islogin = false;
            Double balance, operBal;
            int rows;
            //1685515870916
            switch (choice) {
                case 1:
                    // 执行开户操作
                    System.out.println("您选择了开户操作");
                    System.out.println("请依次输入您的以下信息（密码、姓名、证件号码、手机号码）（以回车符分割）：");
                    String mainID = String.valueOf(System.currentTimeMillis());
                    user.setAccountNum(mainID);
                    user.setPassword(MD5EncryptionUtil.encryptMD5(sc.nextLine()));
                    user.setBal(0);
                    user.setState("1");
                    user.setMakeUserName(sc.nextLine());
                    user.setCertNo(sc.nextLine());
                    user.setMobileNo(sc.nextLine());
                    user.setOpenDt(Date.valueOf(LocalDate.now()));
                    rows = accountoperationService.openAnCount(user);
                    if(rows == 1){
                        System.out.println("开户成功！,账户为："+mainID);
                    }else{
                        System.out.println("开户失败！");
                    }
                    break;
                case 2:
                    // 执行销户操作
                    System.out.println("您选择了销户操作");
                    System.out.println("请输入您的卡号：");
                    user.setAccountNum(sc.nextLine());
                    System.out.println("请输入您的密码：");
                    password = MD5EncryptionUtil.encryptMD5(sc.nextLine());
                    user.setPassword(password);
                    islogin = accountoperationService.login(user);
                    if(!islogin) {
                        System.out.println("登录失败，返回主菜单请重新操作");
                        break;
                    }
                    System.out.println("登录成功！");
                    rows = accountoperationService.cancelTheCount(user);
                    if(rows == 1){
                        System.out.println("销户成功！");
                    }else{
                        System.out.println("销户失败！");
                    }
                    break;
                case 3:
                    // 执行挂失操作
                    System.out.println("您选择了挂失操作");
                    System.out.println("请输入您的卡号：");
                    user.setAccountNum(sc.nextLine());
                    System.out.println("请输入您的密码：");
                    password = MD5EncryptionUtil.encryptMD5(sc.nextLine());
                    user.setPassword(password);
                    islogin = accountoperationService.login(user);
                    if(!islogin) {
                        System.out.println("登录失败，返回主菜单请重新操作");
                        break;
                    }
                    System.out.println("登录成功！");
                    user.setState("0");
                    rows = accountoperationService.reportTheLoss(user);
                    if(rows == 1){
                        System.out.println("挂失成功！");
                    }else{
                        System.out.println("挂失失败！");
                    }

                    break;
                case 4:
                    // 执行补办操作
                    System.out.println("您选择了补办操作");
                    System.out.println("请输入您的卡号：");
                    user.setAccountNum(sc.nextLine());
                    System.out.println("请输入您的密码：");
                    password = MD5EncryptionUtil.encryptMD5(sc.nextLine());
                    user.setPassword(password);
                    islogin = accountoperationService.login(user);
                    if(!islogin) {
                        System.out.println("登录失败，返回主菜单请重新操作");
                        break;
                    }
                    System.out.println("登录成功！");
                    user.setState("1");
                    rows = accountoperationService.reportTheLoss(user);
                    if(rows == 1){
                        System.out.println("补卡成功！");
                    }else{
                        System.out.println("补卡失败！");
                    }
                    break;
                case 5:
                    // 执行修改密码操作
                    System.out.println("您选择了修改密码操作");
                    System.out.println("请输入您的卡号：");
                    user.setAccountNum(sc.nextLine());
                    System.out.println("请输入您的密码：");
                    password = MD5EncryptionUtil.encryptMD5(sc.nextLine());
                    user.setPassword(password);
                    islogin = accountoperationService.login(user);
                    if(!islogin) {
                        System.out.println("密码修改失败，返回主菜单请重新操作，或联系管理员");
                        break;
                    }
                    System.out.println("登录成功！");
                    System.out.println("请输入新密码：");
                    user.setPassword(MD5EncryptionUtil.encryptMD5(sc.nextLine()));
                    accountoperationService.updatePassword(user);
                    System.out.println("密码更改成功！");
                    break;
                case 6:
                    // 执行存款操作
                    System.out.println("您选择了存款操作");
                    System.out.println("请输入您的卡号：");
                    user.setAccountNum(sc.nextLine());
                    System.out.println("请输入您的密码：");
                    password = MD5EncryptionUtil.encryptMD5(sc.nextLine());
                    user.setPassword(password);
                    islogin = accountoperationService.login(user);
                    if(!islogin) {
                        System.out.println("登录失败，返回主菜单请重新操作");
                        break;
                    }
                    System.out.println("登录成功！");
                    System.out.println("请输入存款金额：");
                    operBal = Double.parseDouble(sc.nextLine());
                    user.setBal(operBal);
                    balance = accountoperationService.checkMoney(user);
                    if(null == balance){
                        break;
                    }
                    rows = accountoperationService.depositAndWithrawMoney(user);
                    if(rows == 1){
                        System.out.println("存款成功！余额为："+(balance+operBal));
                    }else{
                        System.out.println("存款失败！");
                    }
                    break;
                case 7:
                    // 执行取款操作
                    System.out.println("您选择了取款操作");
                    System.out.println("请输入您的卡号：");
                    user.setAccountNum(sc.nextLine());
                    System.out.println("请输入您的密码");
                    password = MD5EncryptionUtil.encryptMD5(sc.nextLine());
                    user.setPassword(password);
                    islogin = accountoperationService.login(user);
                    if(!islogin) {
                        System.out.println("登录失败，返回主菜单请重新操作");
                        break;
                    }
                    System.out.println("登录成功！");
                    balance = accountoperationService.checkMoney(user);
                    if(null == balance){
                        break;
                    }
                    System.out.println("请输入取款金额：");
                    operBal = -Double.parseDouble(sc.nextLine());
                    if(-operBal > balance) {
                        System.out.println("余额不足，返回主菜单请重新操作");
                        break;
                    }
                    user.setBal(operBal);
                    rows = accountoperationService.depositAndWithrawMoney(user);
                    if(rows == 1) {
                        System.out.println("取款成功！余额为："+(balance+operBal));
                    }else{
                        System.out.println("取款失败！");
                    }
                    break;
                case 8:
                    // 退出循环
                    System.out.println("您选择了退出");
                    break;
                default:
                    // 输入错误选择
                    System.out.println("输入错误，请重新选择");
            }
            choice = 0;
            System.out.println();
        }

        sc.close();
    }
}