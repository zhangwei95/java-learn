package behavior.interpreter;

/**
 * 抽象表达式
 * 定义解释器的接口，约定解释器的解释操作，主要包含解释方法 interpret()
 */
interface AbstractExpression {
    /**
     * 解释方法
     */
    public Object interpret(String info);
}
