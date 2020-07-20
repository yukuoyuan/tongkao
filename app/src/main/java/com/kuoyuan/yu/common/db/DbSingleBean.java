package com.kuoyuan.yu.common.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created on 2020/7/19
 * 这是一个单选题数据的DB实体类
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
@Entity
public class DbSingleBean implements Parcelable {
    /**
     * id
     */
    @Id
    public Long id;
    /**
     * 标题
     */
    public String title;
    /**
     * 提示语
     */
    public String tip;
    /**
     * 答案
     */
    public String answers;
    /**
     * 类型
     */
    public long type;
    /**
     * 是否错了
     */
    public boolean isWrong;
    /**
     * 是否收藏
     */
    public boolean isCollection;

    private DbSingleBean(Builder builder) {
        id = builder.id;
        title = builder.title;
        tip = builder.tip;
        answers = builder.answers;
        type = builder.type;
        isWrong = builder.isWrong;
        isCollection = builder.isCollection;
    }

    public static final class Builder {
        private Long id;
        private String title;
        private String tip;
        private String answers;
        private long type;
        private boolean isWrong;
        private boolean isCollection;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder tip(String val) {
            tip = val;
            return this;
        }

        public Builder answers(String val) {
            answers = val;
            return this;
        }

        public Builder type(long val) {
            type = val;
            return this;
        }

        public Builder isWrong(boolean val) {
            isWrong = val;
            return this;
        }

        public Builder isCollection(boolean val) {
            isCollection = val;
            return this;
        }

        public DbSingleBean build() {
            return new DbSingleBean(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.tip);
        dest.writeString(this.answers);
        dest.writeLong(this.type);
        dest.writeByte(this.isWrong ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCollection ? (byte) 1 : (byte) 0);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAnswers() {
        return this.answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public long getType() {
        return this.type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public boolean getIsWrong() {
        return this.isWrong;
    }

    public void setIsWrong(boolean isWrong) {
        this.isWrong = isWrong;
    }

    public boolean getIsCollection() {
        return this.isCollection;
    }

    public void setIsCollection(boolean isCollection) {
        this.isCollection = isCollection;
    }

    protected DbSingleBean(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.tip = in.readString();
        this.answers = in.readString();
        this.type = in.readLong();
        this.isWrong = in.readByte() != 0;
        this.isCollection = in.readByte() != 0;
    }

    @Generated(hash = 891013547)
    public DbSingleBean(Long id, String title, String tip, String answers, long type,
            boolean isWrong, boolean isCollection) {
        this.id = id;
        this.title = title;
        this.tip = tip;
        this.answers = answers;
        this.type = type;
        this.isWrong = isWrong;
        this.isCollection = isCollection;
    }

    @Generated(hash = 2096071294)
    public DbSingleBean() {
    }

    public static final Creator<DbSingleBean> CREATOR = new Creator<DbSingleBean>() {
        @Override
        public DbSingleBean createFromParcel(Parcel source) {
            return new DbSingleBean(source);
        }

        @Override
        public DbSingleBean[] newArray(int size) {
            return new DbSingleBean[size];
        }
    };
}
