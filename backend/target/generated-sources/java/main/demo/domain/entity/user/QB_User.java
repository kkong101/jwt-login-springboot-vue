package main.demo.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QB_User is a Querydsl query type for B_User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QB_User extends EntityPathBase<B_User> {

    private static final long serialVersionUID = 2112496564L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QB_User b_User = new QB_User("b_User");

    public final main.demo.domain.basement.QBaseEntity _super = new main.demo.domain.basement.QBaseEntity(this);

    public final StringPath access_token = createString("access_token");

    public final StringPath account_id = createString("account_id");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_at = _super.created_at;

    //inherited
    public final StringPath encrypt = _super.encrypt;

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final main.demo.domain.basement.embed.QPassword password;

    public final StringPath refresh_token = createString("refresh_token");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> update_at = _super.update_at;

    public QB_User(String variable) {
        this(B_User.class, forVariable(variable), INITS);
    }

    public QB_User(Path<? extends B_User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QB_User(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QB_User(PathMetadata metadata, PathInits inits) {
        this(B_User.class, metadata, inits);
    }

    public QB_User(Class<? extends B_User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.password = inits.isInitialized("password") ? new main.demo.domain.basement.embed.QPassword(forProperty("password")) : null;
    }

}

