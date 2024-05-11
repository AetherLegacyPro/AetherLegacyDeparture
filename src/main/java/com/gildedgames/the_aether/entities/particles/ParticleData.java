package com.gildedgames.the_aether.entities.particles;

public enum ParticleData
{
    VX_VY_VZ(new Class[] { Double.TYPE, Double.TYPE, Double.TYPE }) {
        @Override
        public Object[] getArguments(final double motionX, final double motionY, final double motionZ, final float scale) {
            return new Object[] { motionX, motionY, motionZ };
        }
    }, 
    VX_VY_VZ_SCALE(new Class[] { Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE }) {
        @Override
        public Object[] getArguments(final double motionX, final double motionY, final double motionZ, final float scale) {
            return new Object[] { motionX, motionY, motionZ, scale };
        }
    }, 
    V0_V0_V0(new Class[] { Double.TYPE, Double.TYPE, Double.TYPE }) {
        @Override
        public Object[] getArguments(final double motionX, final double motionY, final double motionZ, final float scale) {
            return new Object[] { 0, 0, 0 };
        }
    }, 
    SCALE(new Class[] { Float.TYPE }) {
        @Override
        public Object[] getArguments(final double motionX, final double motionY, final double motionZ, final float scale) {
            return new Object[] { scale };
        }
    }, 
    NONE(new Class[0]) {
        @Override
        public Object[] getArguments(final double motionX, final double motionY, final double motionZ, final float scale) {
            return new Object[0];
        }
    };
    
    private Class<?>[] argumentTypes;
    private int argumentCount;
    
    private ParticleData(final Class<?>[] argumentTypes) {
        this.argumentTypes = argumentTypes;
        this.argumentCount = argumentTypes.length;
    }
    
    public Class<?>[] getArgumentTypes() {
        return this.argumentTypes;
    }
    
    public int getArgumentCount() {
        return this.argumentCount;
    }
    
    public abstract Object[] getArguments(final double p0, final double p1, final double p2, final float p3);
}


