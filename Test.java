import java.util.Scanner;

public class Test {

        private static final int MIN_QUANTITY = 0;
        // 水果斤数最小值
        private static final double APPLE_PRICE = 8.0;
        // 苹果价格
        private static final double STRAWBERRY_PRICE = 13.0;
        // 草莓价格
        private static final double MANGO_PRICE = 20.0;
        // 芒果价格

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            // 创建 Scanner 对象以读取用户输入

            System.out.print("请输入苹果数量（斤）：");
            int apple_size = scanner.nextInt();
            // 读取用户输入的苹果数量

            System.out.print("请输入草莓数量（斤）：");
            int strawberry_size = scanner.nextInt();
            // 读取用户输入的草莓数量

            System.out.print("请输入芒果数量（斤）：");
            int mango_size = scanner.nextInt();
            // 读取用户输入的芒果数量

            System.out.print("请输入草莓价格是否打折：");
            String strawberry_yes = scanner.next();
            // 读取用户输入的草莓价格是否打折
            boolean strawberry_count=false;
            if("Y".equals(strawberry_yes)){
                 strawberry_count=true;
            }

            System.out.print("请输入草莓价格折扣：");
            double strawberry_count_number = scanner.nextDouble();
            // 读取用户输入的草莓价格折扣

            System.out.print("请输入是否满减：");
            String discount_yes_no = scanner.next();
            // 读取用户输入的是否满减
            boolean discount_yes=false;
            if("Y".equals(discount_yes_no)){
                 discount_yes=true;
            }

            System.out.print("请输入满减金额：");
            int amount = scanner.nextInt();
            // 读取用户输入的满减金额

            System.out.print("请输入优惠金额：");
            int amount_pro = scanner.nextInt();
            // 读取用户输入的优惠金额

            // 计算总价
            double totalPrice = calculateTotalPrice(apple_size, strawberry_size,mango_size,strawberry_count,
                    strawberry_count_number,discount_yes,amount,amount_pro
                    );

            //是否满减
            if(true==discount_yes){
                if(totalPrice>=amount){
                    //满减
                    totalPrice-=amount_pro;
                }
            }
            System.out.println("总价为：" + totalPrice + " 元");
            validateTotalPrice( totalPrice,  apple_size,  strawberry_size,  mango_size,  strawberry_count,  strawberry_count_number,  discount_yes,  amount,  amount_pro);
            // 验证计算结果的正确性
        }

        /**
         * 计算总价
         * @param strawberry_size 苹果数量（斤）
         * @param strawberry_size 草莓数量（斤）
         * @param mango_size
         * @param strawberry_count
         * @param strawberry_count_number
         * @param discount_yes
         * @param amount
         * @param amount_pro
         * @return 总价（元）
         */
        public static double calculateTotalPrice(int apple_size, int strawberry_size, int mango_size, boolean strawberry_count, double strawberry_count_number, boolean discount_yes, int amount, int amount_pro) {
            //芒果数量为0
            if(MIN_QUANTITY==mango_size){
                if (apple_size < MIN_QUANTITY || strawberry_size < MIN_QUANTITY) {
                    throw new IllegalArgumentException("水果斤数必须大于等于 " + MIN_QUANTITY + " 斤");
                }
                return APPLE_PRICE * apple_size + STRAWBERRY_PRICE * apple_size;
            }else{
                //芒果数量不为0
                if (apple_size < MIN_QUANTITY || strawberry_size < MIN_QUANTITY||mango_size<MIN_QUANTITY) {
                    throw new IllegalArgumentException("水果斤数必须大于等于 " + MIN_QUANTITY + " 斤");
                }
                //草莓是否打折
                if(true==strawberry_count){
                    return APPLE_PRICE * apple_size + STRAWBERRY_PRICE * apple_size*strawberry_count_number+MANGO_PRICE*mango_size;
                }

                return APPLE_PRICE * apple_size + STRAWBERRY_PRICE * apple_size+MANGO_PRICE*mango_size;
            }

        }

        /**
         * 验证计算结果的正确性
         * @param totalPrice 总价（元）
         * @param apple_size 苹果数量（斤）
         * @param strawberry_size 草莓数量（斤）
         */
        public static void validateTotalPrice(double totalPrice, int apple_size, int strawberry_size, int mango_size, boolean strawberry_count, double strawberry_count_number, boolean discount_yes, int amount, int amount_pro) {
            double expectedTotalPrice = calculateTotalPrice(apple_size, strawberry_size, mango_size, strawberry_count, strawberry_count_number, discount_yes, amount, amount_pro);
            //是否满减
            if(discount_yes){
                if(expectedTotalPrice>=amount){
                    //满减
                    expectedTotalPrice-=amount_pro;
                }
            }
            if (totalPrice != expectedTotalPrice) {
                throw new IllegalStateException("计算结果不正确，实际总价为 " + totalPrice + " 元，预期总价为 " + expectedTotalPrice + " 元");
            }
        }
    }

