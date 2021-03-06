/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein
 * is confidential and proprietary to MediaTek Inc. and/or its licensors.
 * Without the prior written permission of MediaTek inc. and/or its licensors,
 * any reproduction, modification, use or disclosure of MediaTek Software,
 * and information contained herein, in whole or in part, shall be strictly prohibited.
 */
/* MediaTek Inc. (C) 2010. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER ON
 * AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
 * NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
 * SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
 * SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES TO LOOK ONLY TO SUCH
 * THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. RECEIVER EXPRESSLY ACKNOWLEDGES
 * THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES
 * CONTAINED IN MEDIATEK SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK
 * SOFTWARE RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND
 * CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
 * AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
 * OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY RECEIVER TO
 * MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek Software")
 * have been modified by MediaTek Inc. All revisions are subject to any receiver's
 * applicable license agreements with MediaTek Inc.
 */

package com.mediatek.engineermode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.mediatek.xlog.Xlog;
/* Vanzo:tanglei on: Mon, 19 Jan 2015 10:29:53 +0800
 */
import com.mediatek.engineermode.sensor.PSensorCalibrationSelect;
// End of Vanzo:tanglei

public final class EngineerModeReceiver extends BroadcastReceiver {

    private static final String TAG = "EM/SECRET_CODE";
    private static final String SECRET_CODE_ACTION = "android.provider.Telephony.SECRET_CODE";
    // process *#*#3646633#*#*
    private final Uri mEmUri = Uri.parse("android_secret_code://3646633");
/* Vanzo:tanglei on: Mon, 19 Jan 2015 10:30:21 +0800
 */
    private final Uri mDiUri = Uri.parse("android_secret_code://9646633");//Device Info
    private final Uri mPscUri = Uri.parse("android_secret_code://772");//PSensor Calibration
// End of Vanzo:tanglei

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            Xlog.i(TAG, "Null action");
            return;
        }
        if (intent.getAction().equals(SECRET_CODE_ACTION)) {
            Uri uri = intent.getData();
            Xlog.i(TAG, "getIntent success in if");
            if (uri.equals(mEmUri)) {
                Intent intentEm = new Intent(context, EngineerMode.class);
                intentEm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Xlog.i(TAG, "Before start EM activity");
                context.startActivity(intentEm);
/* Vanzo:tanglei on: Mon, 19 Jan 2015 10:31:22 +0800
 */
            } else if (uri.equals(mDiUri)) {
                Intent intentDi = new Intent(context, DeviceInfoSettings.class);
                intentDi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Xlog.i(TAG, "Before start Device info activity");
                context.startActivity(intentDi);
            } else if (uri.equals(mPscUri)) {
                Intent intentPsc = new Intent(context, PSensorCalibrationSelect.class);
                intentPsc.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Xlog.i(TAG, "Before start Psensor Calibration activity");
                context.startActivity(intentPsc);
// End of Vanzo:tanglei
            } else {
                Xlog.i(TAG, "No matched URI!");
            }
        } else {
            Xlog.i(TAG, "Not SECRET_CODE_ACTION!");
        }
    }
}
