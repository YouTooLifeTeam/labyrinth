varying vec2 v_texCoord0;
uniform vec2 u_resolution;
uniform sampler2D u_sampler2D;

void main(void) {
	vec2 onePixel =  1.0 / u_resolution;

	vec2 texCoord = v_texCoord0;

	vec3 color = vec3(0.5);
	
	color += texture2D(u_sampler2D, texCoord - onePixel) * 1;
	color -= texture2D(u_sampler2D, texCoord + onePixel) * 1;

	color.rgb = vec3((color.r+color.g+color.b)/3.0);
	//color.rgb+= texture2D(u_sampler2D, v_texCoord0).rgb * vec3(0.1);

	gl_FragColor = vec4(color,texture2D(u_sampler2D, v_texCoord0).a);

}
