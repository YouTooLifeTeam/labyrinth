varying vec4 vColor;
varying vec2 vTexCoord;


uniform mat4 u_projTrans;
uniform sampler2D u_texture;   //diffuse map

void main() {
	gl_FragColor = texture2D(u_texture, vTexCoord);
}