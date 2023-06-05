package service;

import entity.CardInfoEntity;

public interface AccountoperationService {
    int openAnCount(CardInfoEntity user);

    int cancelTheCount(CardInfoEntity user);

    int reportTheLoss(CardInfoEntity user);

    int updatePassword(CardInfoEntity user);

    Double checkMoney(CardInfoEntity user);

    boolean login(CardInfoEntity user);
    boolean logout(CardInfoEntity user);

    int depositAndWithrawMoney(CardInfoEntity user);
}
