#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP 
#endif

#define PI 3.14
varying vec2 vTexCoord0;
varying LOWP vec4 vColor;
 
uniform sampler2D u_texture;
uniform vec2 resolution;
 
uniform float softShadows;
 
uniform float u_noise;
 
float sample(vec2 coord, float r) {
  return step(r, texture2D(u_texture, coord).r);
}
 
void main(void) {
    
	vec2 norm = vTexCoord0.st * 2.0 - 1.0;
	float theta = atan(norm.y, norm.x);
	float r = length(norm);	
	float coord = (theta + PI) / (2.0*PI);
	
	vec2 tc = vec2(coord, 0.0);
	
	float center = sample(vec2(tc.x, tc.y), r);        
	
	float blur = (1./(resolution.x))  * smoothstep(0., 1., r); 
	
	float sum = 0.0;
	
	sum += sample(vec2(tc.x - 4.0*blur, tc.y), r) * 0.05;
	sum += sample(vec2(tc.x - 3.0*blur, tc.y), r) * 0.09;
	sum += sample(vec2(tc.x - 2.0*blur, tc.y), r) * 0.12;
	sum += sample(vec2(tc.x - 1.0*blur, tc.y), r) * 0.15;
	
	sum += center * 0.16;
	
	sum += sample(vec2(tc.x + 1.0*blur, tc.y), r) * 0.15;
	sum += sample(vec2(tc.x + 2.0*blur, tc.y), r) * 0.12;
	sum += sample(vec2(tc.x + 3.0*blur, tc.y), r) * 0.09;
	sum += sample(vec2(tc.x + 4.0*blur, tc.y), r) * 0.05;
	
 	float lit = mix(center, sum, softShadows);
 	
 	gl_FragColor = vColor * vec4(vec3(1.0), lit * smoothstep(1.0, 0.0, r));
}