package dao;

import entity.CardInfoEntity;

public interface AccountOperationMapper {
    int openAnCount(CardInfoEntity user);

    int cancelTheCount(CardInfoEntity user);

    int reportTheLoss(CardInfoEntity user);

    int updatePassword(CardInfoEntity user);

    boolean login(CardInfoEntity user);

    int depositAndWithrawMoney(CardInfoEntity user);

    double checkMoney(CardInfoEntity user);
}
