package com.kuoyuan.yu.compute.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class ComputerSingleCheckListBean {
    public List<ComputerSingleDataBean> data;

    public static class ComputerSingleDataBean implements Parcelable {
        public String title;
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

            public static final Parcelable.Creator<ComputerSingleCheckDataBean> CREATOR = new Parcelable.Creator<ComputerSingleCheckDataBean>() {
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
            dest.writeTypedList(this.checkData);
        }

        public ComputerSingleDataBean() {
        }

        protected ComputerSingleDataBean(Parcel in) {
            this.title = in.readString();
            this.checkData = in.createTypedArrayList(ComputerSingleCheckDataBean.CREATOR);
        }

        public static final Parcelable.Creator<ComputerSingleDataBean> CREATOR = new Parcelable.Creator<ComputerSingleDataBean>() {
            @Override
            public ComputerSingleDataBean createFromParcel(Parcel source) {
                return new ComputerSingleDataBean(source);
            }

            @Override
            public ComputerSingleDataBean[] newArray(int size) {
                return new ComputerSingleDataBean[size];
            }
        };
    }
}
