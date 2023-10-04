package com.colatina.app.service.configuration.mapper;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.WalletDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.dataprovider.entity.AccountEntity;
import com.colatina.app.service.dataprovider.entity.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-04T19:36:26-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8.1 (Azul Systems, Inc.)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountEntity toEntity(AccountDomain dto) {
        if ( dto == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( dto.getId() );
        accountEntity.setName( dto.getName() );
        accountEntity.setLastName( dto.getLastName() );
        accountEntity.setDocument( dto.getDocument() );
        if ( dto.getStatus() != null ) {
            accountEntity.setStatus( dto.getStatus().name() );
        }
        accountEntity.setBirthDate( dto.getBirthDate() );
        accountEntity.setWallet( walletDomainToWalletEntity( dto.getWallet() ) );

        return accountEntity;
    }

    @Override
    public AccountDomain toDto(AccountEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AccountDomain accountDomain = new AccountDomain();

        accountDomain.setId( entity.getId() );
        accountDomain.setName( entity.getName() );
        accountDomain.setLastName( entity.getLastName() );
        accountDomain.setDocument( entity.getDocument() );
        if ( entity.getStatus() != null ) {
            accountDomain.setStatus( Enum.valueOf( AccountStatus.class, entity.getStatus() ) );
        }
        accountDomain.setBirthDate( entity.getBirthDate() );
        accountDomain.setWallet( walletEntityToWalletDomain( entity.getWallet() ) );

        return accountDomain;
    }

    @Override
    public List<AccountEntity> toEntity(List<AccountDomain> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<AccountEntity> list = new ArrayList<AccountEntity>( dtoList.size() );
        for ( AccountDomain accountDomain : dtoList ) {
            list.add( toEntity( accountDomain ) );
        }

        return list;
    }

    @Override
    public List<AccountDomain> toDto(List<AccountEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AccountDomain> list = new ArrayList<AccountDomain>( entityList.size() );
        for ( AccountEntity accountEntity : entityList ) {
            list.add( toDto( accountEntity ) );
        }

        return list;
    }

    protected WalletEntity walletDomainToWalletEntity(WalletDomain walletDomain) {
        if ( walletDomain == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setId( walletDomain.getId() );
        walletEntity.setBalance( walletDomain.getBalance() );

        return walletEntity;
    }

    protected WalletDomain walletEntityToWalletDomain(WalletEntity walletEntity) {
        if ( walletEntity == null ) {
            return null;
        }

        WalletDomain walletDomain = new WalletDomain();

        walletDomain.setId( walletEntity.getId() );
        walletDomain.setBalance( walletEntity.getBalance() );

        return walletDomain;
    }
}
