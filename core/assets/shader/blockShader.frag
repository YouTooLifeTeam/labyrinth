varying vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D u_sampler2D;

void main() {
	gl_FragColor = texture2D(u_sampler2D, v_texCoord0) * v_color * vec4(0.3,0.3,0.3,1.);
}