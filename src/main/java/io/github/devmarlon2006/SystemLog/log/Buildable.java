package io.github.devmarlon2006.SystemLog.log;

import java.util.function.UnaryOperator;

public interface Buildable<P , H extends Handle> {

    /**
     * @param"P" -> Classe a ser construida
     * @param"H" -> Handle a ser configurada
     */

    abstract P build();

    abstract H configure(UnaryOperator<H> configuration);

}