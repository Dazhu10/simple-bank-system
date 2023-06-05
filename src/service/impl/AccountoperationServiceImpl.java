package service.impl;

import dao.AccountOperationMapper;
import dao.impl.AccountOperationMapperImpl;
import entity.CardInfoEntity;
import service.AccountoperationService;

public class AccountoperationServiceImpl implements AccountoperationService {
    AccountOperationMapper accountOperationMapper = new AccountOperationMapperImpl();
    @Override
    public int openAnCount(CardInfoEntity user) {
        return accountOperationMapper.openAnCount(user);
    }

    @Override
    public int cancelTheCount(CardInfoEntity user) {
        return accountOperationMapper.cancelTheCount(user);
    }

    @Override
    public int reportTheLoss(CardInfoEntity user) {
        return accountOperationMapper.reportTheLoss(user);
    }

    @Override
    public int updatePassword(CardInfoEntity user) {
        return accountOperationMapper.updatePassword(user);
    }

    @Override
    public Double checkMoney(CardInfoEntity user) {
        CardInfoEntity v = accountOperationMapper.selctUser(user);
        if(null == v) {
            System.out.println("操作失败，请检查账号是否被挂失！");
            return null;
        }
        return v.getBal();
    }

    @Override
    public boolean login(CardInfoEntity user) {
        return accountOperationMapper.login(user);
    }

    @Override
    public boolean logout(CardInfoEntity user) {
        return false;
    }

    @Override
    public int depositAndWithrawMoney(CardInfoEntity user) {
        return accountOperationMapper.depositAndWithrawMoney(user);
    }


}
