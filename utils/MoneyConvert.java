package cn.iocoder.yudao.module.clientManagement.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 分、元转换
 * @author BOZI
 * @date 2023/01/31
 */
public class MoneyConvert {
    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String fen2YuanStr(String amount) {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new RuntimeException("金额格式错误|"+amount);
        }
        return formatFen(BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)));
    }

    /**
     * 格式化数字
     * @param fen
     * @return
     */
    private static String formatFen(BigDecimal fen){
        DecimalFormat df1 = new DecimalFormat("0.00");

        return df1.format(fen);
    }

    /**
     * 将元为单位的参数转换为分 , 只对小数点前2位支持
     *
     * @param yuan
     * @return
     * @throws Exception
     */
    public static String yuan2FenInt(String yuan){
        BigDecimal fenBd = new BigDecimal(yuan).multiply(new BigDecimal(100));
        fenBd = fenBd.setScale(0, BigDecimal.ROUND_HALF_UP);

        return String.valueOf(fenBd.intValue());
    }

}