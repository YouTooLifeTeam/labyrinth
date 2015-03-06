//attributes from vertex shader
varying vec4 vColor;
varying vec2 vTexCoord;

//our texture samplers
uniform sampler2D u_texture;   //diffuse map
uniform sampler2D u_normals;   //normal map

//values used for shading algorithm...
uniform vec2 Resolution;         //resolution of screen
uniform vec3 LightPos[16];           //light position, normalized
uniform vec4 LightColor[16];    //light RGBA -- alpha is intensity
uniform vec4 AmbientColor;  //ambient RGBA -- alpha is intensity 
uniform vec3 Falloff;            //attenuation coefficients
uniform int lights;

void main() {
	vec3 Sum = vec3(0.0);
	vec4 DiffuseColor = texture2D(u_texture, vTexCoord);
	vec3 NormalMap = texture2D(u_normals, vTexCoord).rgb;
	vec3 N = normalize(NormalMap * 2.0 - 1.0);
	vec3 Ambient = AmbientColor.rgb * AmbientColor.a;
	vec3 FinalColor = vec3(0.0);
	for(int i = 0; i<lights; i++){
    	vec3 LightDir = vec3(LightPos[i].xy - (gl_FragCoord.xy / Resolution.xy), LightPos[i].z);
    	LightDir.x *= Resolution.x / Resolution.y;
    	float D = length(LightDir);
    	vec3 L = normalize(LightDir);
    	vec3 Diffuse = (LightColor[i].rgb * LightColor[i].a) * max(dot(N, L), 0.0);
		float Attenuation = 1.0 / ( Falloff.x + (Falloff.y*D) + (Falloff.z*D*D) );
    	vec3 Intensity = Ambient + Diffuse * Attenuation;
    	FinalColor = DiffuseColor.rgb * Intensity;
		Sum = Sum + FinalColor;
	}
	gl_FragColor = vColor * vec4(Sum, DiffuseColor.a);
}