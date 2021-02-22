package dev.the_fireplace.lib.impl.config;

import dev.the_fireplace.lib.api.storage.access.intermediary.StorageReadBuffer;
import dev.the_fireplace.lib.api.storage.access.intermediary.StorageWriteBuffer;
import dev.the_fireplace.lib.api.storage.lazy.LazyConfig;
import dev.the_fireplace.lib.api.storage.lazy.LazyConfigInitializer;
import dev.the_fireplace.lib.impl.FireplaceLib;

public final class FLConfig extends LazyConfig {
    private static final FLConfig INSTANCE = LazyConfigInitializer.lazyInitialize(new FLConfig());
    private static final FLConfig DEFAULT_INSTANCE = new FLConfig();
    private final Access access = new Access();

    public static FLConfig getInstance() {
        return INSTANCE;
    }
    public static Access getData() {
        return INSTANCE.access;
    }
    static Access getDefaultData() {
        return DEFAULT_INSTANCE.access;
    }

    private FLConfig() {}

    private String locale = "en_us";

    private short essentialThreadPoolSize = 256;
    private short nonEssentialThreadPoolSize = 128;

    @Override
    public void readFrom(StorageReadBuffer buffer) {
        locale = buffer.readString("locale", locale);
        essentialThreadPoolSize = buffer.readShort("essentialThreadPoolSize", essentialThreadPoolSize);
        nonEssentialThreadPoolSize = buffer.readShort("nonEssentialThreadPoolSize", nonEssentialThreadPoolSize);
    }

    @Override
    public void writeTo(StorageWriteBuffer buffer) {
        buffer.writeString("locale", locale);
        buffer.writeShort("essentialThreadPoolSize", essentialThreadPoolSize);
        buffer.writeShort("nonEssentialThreadPoolSize", nonEssentialThreadPoolSize);
    }

    @Override
    public String getId() {
        return FireplaceLib.MODID;
    }

    void resave() {
        save();
    }

    public final class Access {
        private Access(){}
        public String getLocale() {
            return locale;
        }

        public short getEssentialThreadPoolSize() {
            return essentialThreadPoolSize;
        }

        public short getNonEssentialThreadPoolSize() {
            return nonEssentialThreadPoolSize;
        }


        public void setLocale(String locale) {
            FLConfig.this.locale = locale;
        }

        public void setEssentialThreadPoolSize(short essentialThreadPoolSize) {
            FLConfig.this.essentialThreadPoolSize = essentialThreadPoolSize;
        }

        public void setNonEssentialThreadPoolSize(short nonEssentialThreadPoolSize) {
            FLConfig.this.nonEssentialThreadPoolSize = nonEssentialThreadPoolSize;
        }
    }
}