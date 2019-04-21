import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalOperations  {

    public static NamedBiFunction<Double, Double,Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public Double apply(Double o, Double o2) {
            return o + o2;
        }

        @Override
        public String name() {
            return "add";
        }
    };

    public static NamedBiFunction<Double, Double,Double> subtract = new NamedBiFunction<Double, Double,Double>() {
        @Override
        public String name() {
            return "diff";
        }

        @Override
        public Double apply(Double o, Double o2) {
            return o - o2;
        }
    };

    public static NamedBiFunction<Double, Double,Double> multiply = new NamedBiFunction<Double, Double,Double>() {
        @Override
        public String name() {
            return "mult";
        }

        @Override
        public Double apply(Double o, Double o2) {
            return o*o2;
        }
    };

    public static NamedBiFunction<Double, Double,Double> divide = new NamedBiFunction<Double, Double,Double>() {
        @Override
        public String name() {
            return "div";
        }

        @Override
        public Double apply(Double o, Double o2) {
            if(o2 != 0) {
                return o / o2;
            } else {
                throw new ArithmeticException("Can't divide by zero");
            }

        }
    };


    interface NamedBiFunction<i, j,k> extends BiFunction<i, j, k> {
        String name();

    }

    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a
     * certain type, and produce a single instance of that type -- to a list of
     * arguments of that type. The functions are applied in an iterative manner, and
     * the result of each function is stored in the list in an iterative manner as
     * well, to be used by the next bifunction in the next iteration.
     * For example, given
     * List<Integer> args = [1,1,3,0,4], and
     * List<BiFunction<Double, Double, Double>> bfs = [add, multiply, add, divide],
     * <code>zip(args, bfs)</code> will proceed iteratively as follows:
     * - index 0: the result of add(1,1) is stored in args[1] to yield args = [1,2,3,0,4]
     * - index 1: the result of multiply(2,3) is stored in args[2] to yield args = [1,2,6,0,4]
     * - index 2: the result of add(6,0) is stored in args[3] to yield args = [1,2,6,6,4]
     * - index 3: the result of divide(6,4) is stored in args[4] to yield args = [1,2,6,6,1]
     *
     * @param args: the arguments over which <code>bifunctions</code>
     * will be iteratively applied.
     * @param bifunctions: the given list of bifunctions that will iteratively be
     * applied on the <code>args</code>.
     * @param <T>: the type parameter of the arguments (e.g., Integer, Double)
     * @return the last element in <code>args</code>, the final result of
     * all the bifunctions being applied in sequence.
     */
    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) {

        /*
        for i = 0 ro bifunctions.size -1
            apply bifunctions[i] ro args[i and i+1]
            store result in args[i+1]
        return args[args.length-1]
         */

        for(int i = 0; i < bifunctions.size()-1; i++) {
            args.add(i+1,bifunctions.get(i).apply(args.get(i), args.get(i+1)));
        }

        return args.get(args.size()-1);

    }

    public static class FunctionComposition<i,j,k> {
        //BiFunction<Double,Double, Double> composition;
        BiFunction<Function<i, j>, Function<j, k>, Function<i, k>> composition = new BiFunction<Function<i, j>, Function<j, k>, Function<i, k>>(){

            @Override
            public Function<i, k> apply(Function<i, j> ijFunction, Function<j, k> jkFunction) {
                return ijFunction.andThen(jkFunction);
            }
        };

    }


}
