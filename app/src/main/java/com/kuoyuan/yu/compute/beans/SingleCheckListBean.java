package com.kuoyuan.yu.compute.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class SingleCheckListBean {
    public List<SingleDataBean> data;

    public static class SingleDataBean implements Parcelable {
        public String title;
        public String tip;
        public List<ComputerSingleCheckDataBean> checkData;

        public static class ComputerSingleCheckDataBean implements Parcelable {
            public String title;
            public boolean isRight;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.title);
                dest.writeByte(this.isRight ? (byte) 1 : (byte) 0);
            }

            public ComputerSingleCheckDataBean() {
            }

            protected ComputerSingleCheckDataBean(Parcel in) {
                this.title = in.readString();
                this.isRight = in.readByte() != 0;
            }

            public static final Creator<ComputerSingleCheckDataBean> CREATOR = new Creator<ComputerSingleCheckDataBean>() {
                @Override
                public ComputerSingleCheckDataBean createFromParcel(Parcel source) {
                    return new ComputerSingleCheckDataBean(source);
                }

                @Override
                public ComputerSingleCheckDataBean[] newArray(int size) {
                    return new ComputerSingleCheckDataBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.tip);
            dest.writeTypedList(this.checkData);
        }

        public SingleDataBean() {
        }

        protected SingleDataBean(Parcel in) {
            this.title = in.readString();
            this.tip = in.readString();
            this.checkData = in.createTypedArrayList(ComputerSingleCheckDataBean.CREATOR);
        }

        public static final Creator<SingleDataBean> CREATOR = new Creator<SingleDataBean>() {
            @Override
            public SingleDataBean createFromParcel(Parcel source) {
                return new SingleDataBean(source);
            }

            @Override
            public SingleDataBean[] newArray(int size) {
                return new SingleDataBean[size];
            }
        };
    }

}
