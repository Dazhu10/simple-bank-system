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
    public double checkMoney(CardInfoEntity user) {
        return accountOperationMapper.checkMoney(user);
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
