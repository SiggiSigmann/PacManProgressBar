from PIL import Image
import os


def process_gif_folder(input_folder):
    os.makedirs(input_folder, exist_ok=True)
    for file in os.listdir(input_folder):
        if file.lower().endswith(".gif"):
            gif_path = os.path.join(input_folder, file)

            gif_name = os.path.splitext(os.path.basename(gif_path))[0]
            gif_output_folder = os.path.join(output_folder, gif_name)
            os.makedirs(gif_output_folder, exist_ok=True)

            # Open the GIF file
            with Image.open(gif_path) as img:
                frame = 0
                total_frames = img.n_frames
                print(f"Processing {gif_path}: {total_frames} frames found")

                while True:
                    frame_path = os.path.join(gif_output_folder, f"{frame}.png")
                    img.save(frame_path, format="PNG")
                    print(f"Saved: {frame_path}")

                    frame += 1
                    try:
                        img.seek(frame)  # Move to the next frame
                    except EOFError:
                        break  # No more frames, exit loop

# Example usage
input_folder = "/Users/tobias.sigmann/workspace/plugin/PacManProgressBar/src/main/resources/pacMan"  # Folder containing GIFs


process_gif_folder(input_folder)
